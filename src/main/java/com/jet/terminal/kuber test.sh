 set -x
      EXPECTED_SECRET=kuttl-medium-mysql-instance

      # $1: field name (e.f. "host"
      function assert_non_empty_secret_field() {
          FIELD_NAME=$1
          FIELD_VALUE=$(kubectl get secrets/${EXPECTED_SECRET} -n "$NAMESPACE" -o jsonpath='{.data.${FIELD_NAME}}' | base64 --decode)
          if [ -z "${FIELD_VALUE}" ]; then
              echo "missing ${FIELD_NAME} value"
              exit 1
          else
              echo "${FIELD_NAME} is: ${FIELD_VALUE}"
          fi

      }
      assert_non_empty_secret_field "host"
      assert_non_empty_secret_field "password"
      assert_non_empty_secret_field "username"